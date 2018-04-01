package com.xsn.explorer.data

import com.xsn.explorer.data.anorm.BlockPostgresDataHandler
import com.xsn.explorer.data.anorm.dao.BlockPostgresDAO
import com.xsn.explorer.data.common.PostgresDataHandlerSpec
import com.xsn.explorer.helpers.BlockLoader

class BlockPostgresDataHandlerSpec extends PostgresDataHandlerSpec {

  lazy val dataHandler = new BlockPostgresDataHandler(database, new BlockPostgresDAO)

  "create" should {
    "add a new block" in {
      // PoS block
      val block = BlockLoader.get("1ca318b7a26ed67ca7c8c9b5069d653ba224bf86989125d1dfbb0973b7d6a5e0")

      val result = dataHandler.create(block)
      result.isGood mustEqual true
    }

    "override an existing block" in {
      val block = BlockLoader.get("1ca318b7a26ed67ca7c8c9b5069d653ba224bf86989125d1dfbb0973b7d6a5e0")
      dataHandler.create(block)

      val newBlock = BlockLoader.get("25762bf01143f7fe34912c926e0b95528b082c6323de35516de0fc321f5d8058").copy(hash = block.hash)
      val result = dataHandler.create(newBlock)
      result.isGood mustEqual true


    }
  }
}

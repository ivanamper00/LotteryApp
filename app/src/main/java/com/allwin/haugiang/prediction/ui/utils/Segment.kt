package com.allwin.haugiang.prediction.ui.utils

import androidx.annotation.Keep
import com.allwin.haugiang.R
import com.allwin.haugiang.prediction.domain.model.SegmentModel

@Keep
enum class Segment(val segmentModel: SegmentModel) {
    MEGA(
        SegmentModel(
            name = "Mega 6/45",
            imageResource = R.drawable.ic_mega645,
            details = "Chọn 6 con số từ 01 đến 45 để giành giải Jackpot tối thiểu 12 tỷ đồng."
        )
    ),
    POWER(
        SegmentModel(
            name = "Power 6/55",
            imageResource = R.drawable.ic_power655,
            details = "chọn 6 số từ 01 đến 55 để trúng giải Jackpot 1 trị giá 30 tỷ đồng và giải Jackpot 2 trị giá 3 tỷ đồng"
        )
    ),
    MAX(
        SegmentModel(
            name = "Max 3D",
            imageResource = R.drawable.ic_max3d,
            details = "Chọn dãy số có 3 chữ số 000-999 để có cơ hội nhận những giải thưởng hấp dẫn."
        )
    ),
    KENO(
        SegmentModel(
            name = "KENO",
            imageResource = R.drawable.ic_keno,
            details = "Cơ hội trúng 2 tỷ chỉ với 10.000 đồng - Kết quả trúng thưởng vẫn không trùng khớp.\n" +
                    "Keno được phát hành từ 06:00 và kết thúc muộn nhất vào 21h55 các ngày từ thứ 2 đến chủ nhật với tần suất 10 phút / kỳ, bán vé liên tục trong suốt thời gian của mỗi đợt."
        )
    )
}
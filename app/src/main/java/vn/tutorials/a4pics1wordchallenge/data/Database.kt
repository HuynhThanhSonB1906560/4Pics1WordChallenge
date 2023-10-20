package vn.tutorials.a4pics1wordchallenge.data

import vn.tutorials.a4pics1wordchallenge.R
import vn.tutorials.a4pics1wordchallenge.model.Pictures


class Database private constructor() {
    val allQuestions: ArrayList<Pictures>
        get() {
            val pictures: ArrayList<Pictures> = ArrayList<Pictures>()
            pictures.add(
                Pictures(
                    R.drawable.ball_one,
                    R.drawable.ball_two,
                    R.drawable.ball_three,
                    R.drawable.ball_four,
                    "ball",
                    "lmrvuxntlbshak"
                )
            )
            pictures.add(
                Pictures(
                    R.drawable.tea_one,
                    R.drawable.tea_two,
                    R.drawable.tea_three,
                    R.drawable.tea_four,
                    "tea",
                    "phtaegjxrokivf"
                )
            )
            pictures.add(
                Pictures(
                    R.drawable.image11,
                    R.drawable.image12,
                    R.drawable.image13,
                    R.drawable.image14,
                    "apple",
                    "phtpaeglxrokiv"
                )
            )
            pictures.add(
                Pictures(
                    R.drawable.image31,
                    R.drawable.image32,
                    R.drawable.image33,
                    R.drawable.image34,
                    "shadow",
                    "sdhaiocwlfvcnz"
                )
            )

            pictures.add(
                Pictures(
                    R.drawable.image41,
                    R.drawable.image42,
                    R.drawable.image43,
                    R.drawable.image44,
                    "window",
                    "pbstionshdwcwh"
                )
            )

            pictures.add(
                Pictures(
                    R.drawable.image61,
                    R.drawable.image62,
                    R.drawable.image63,
                    R.drawable.image64,
                    "mouse",
                    "ouzimdgewxabvs"
                )
            )

            pictures.add(
                Pictures(
                    R.drawable.image91,
                    R.drawable.image92,
                    R.drawable.image93,
                    R.drawable.image94,
                    "italy",
                    "wtualyhgonioef"
                )
            )
            pictures.add(
                Pictures(
                    R.drawable.image101,
                    R.drawable.image102,
                    R.drawable.image103,
                    R.drawable.image104,
                    "seasons",
                    "sdoeasncsxbghx"
                )
            )
            pictures.add(
                Pictures(
                    R.drawable.image111,
                    R.drawable.image112,
                    R.drawable.image113,
                    R.drawable.image114,
                    "rain",
                    "rdianfkglcgdmz"
                )
            )
            pictures.add(
                Pictures(
                    R.drawable.image121,
                    R.drawable.image122,
                    R.drawable.image123,
                    R.drawable.image124,
                    "glass",
                    "glcnfasczswopq"
                )
            )
            pictures.add(
                Pictures(
                    R.drawable.image131,
                    R.drawable.image132,
                    R.drawable.image133,
                    R.drawable.image134,
                    "smile",
                    "srwmidhglfgebv"
                )
            )
            pictures.add(
                Pictures(
                    R.drawable.image141,
                    R.drawable.image142,
                    R.drawable.image143,
                    R.drawable.image144,
                    "cat",
                    "crptemaloplfdz"
                )
            )
            pictures.add(
                Pictures(
                    R.drawable.image151,
                    R.drawable.image152,
                    R.drawable.image153,
                    R.drawable.image154,
                    "lamp",
                    "srpwemalwuysck"
                )
            )
            pictures.add(
                Pictures(
                    R.drawable.image161,
                    R.drawable.image162,
                    R.drawable.image163,
                    R.drawable.image165,
                    "coin",
                    "cdfgoildnzwqpf"
                )
            )
            pictures.add(
                Pictures(
                    R.drawable.image171,
                    R.drawable.image172,
                    R.drawable.image173,
                    R.drawable.image174,
                    "music",
                    "srhgfyaupiemac"
                )
            )
            pictures.add(
                Pictures(
                    R.drawable.image181,
                    R.drawable.image182,
                    R.drawable.image183,
                    R.drawable.image184,
                    "blinds",
                    "bridntyslpxajg"
                )
            )
            return pictures
        }

    companion object {
        private var database: Database? = null
        val instance: Database?
            get() {
                if (database == null) {
                    database = Database()
                }
                return database
            }
    }
}

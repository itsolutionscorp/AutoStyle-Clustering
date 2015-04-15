class Fixnum
  # Explicitly handle all cases requiring negative notation here
  ROMAN_NUMERALS = [
    [ 'M',  1000 ],
    [ 'CM', 900  ],
    [ 'D',  500  ],
    [ 'CD', 400  ],
    [ 'C',  100  ],
    [ 'XC', 90   ],
    [ 'L',  50   ],
    [ 'XL', 40   ],
    [ 'X',  10   ],
    [ 'IX', 9    ],
    [ 'V',  5    ],
    [ 'IV', 4    ],
    [ 'I',  1    ]
  ]

  def to_roman
    remainder = self

    ''.tap do |str|
      ROMAN_NUMERALS.each do |numeral, value|
        if remainder >= value
          occurances = remainder / value   # Number of whole numeral occurances
          remainder -= occurances * value  # Decreate remainder by whole numeral values
          str << numeral * occurances      # Generate numeral sequence and append to result
        end
      end
    end
  end
end

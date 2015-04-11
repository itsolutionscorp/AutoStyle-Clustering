class Fixnum
  def to_roman
    string = "I" * self

    string.gsub("I" * 1000, "M").
           gsub("I" * 900,  "CM").
           gsub("I" * 500,  "D").
           gsub("I" * 400,  "CD").
           gsub("I" * 100,  "C").
           gsub("I" * 90,   "XC").
           gsub("I" * 50,   "L").
           gsub("I" * 40,   "XL").
           gsub("I" * 10,   "X").
           gsub("I" * 9,    "IX").
           gsub("I" * 6,    "VI").
           gsub("I" * 5,    "V").
           gsub("I" * 4,    "IV")
  end
end

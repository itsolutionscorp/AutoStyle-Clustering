class Fixnum
  def to_roman
    value = self
    result = ""
    numerals = [
	       ['M', 1000],
	       ['CM', 900],
	       ['D', 500],
	       ['CD', 400],
	       ['C', 100],
	       ['XC', 90],
	       ['L', 50],
	       ['XL', 40],
	       ['X', 10],
	       ['IX', 9],
	       ['V', 5],
	       ['IV', 4],
	       ['I', 1]
                        ]
    if value > 0 && value <= 3000
      numerals.each do |roman, arabic|
        while value >= arabic
	  result += roman
	  value -= arabic
        end
      end
    result
    end
  end
end

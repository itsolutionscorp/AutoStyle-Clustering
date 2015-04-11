# 1 point: "A", "E", "I", "O", "U", "L", "N", "R", "S", "T",
# 2 points: "D", "G",
# 3 points: "B", "C", "M", "P",
# 4 points: "F", "H", "V", "W", "Y",
# 5 points: "K",
# 8 points: "J", "X",
# 10 points: "Q", "Z",
class ETL
  def self.transform orig
    res = {}
    orig.each do |key, value|
      value.each do |letter|
        res[letter.downcase] = key
      end
    end
    res
  end
end

class Hamming
  class << self
    def compute(string_one, string_two)
      return false if string_one.length != string_two.length
      count = 0
      string_one.split("").each_with_index do |one, index|
        count += 1 if one != string_two[index]
      end
      count      
    end
  end
end

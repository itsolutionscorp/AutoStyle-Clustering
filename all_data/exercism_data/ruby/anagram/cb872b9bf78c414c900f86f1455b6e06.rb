class Anagram
  def initialize(string)
    @string = string
  end

  def match(string_array)
    string_normalized = normalize(@string)
    string_orderded   = order(string_normalized)

    string_array.select do |word|
      word = normalize(word)

      word != string_normalized && order(word) == string_orderded
    end
  end


  private
    def order(string)
      string.split("").sort.join
    end

    def normalize(string)
      string.to_s.downcase
    end
end

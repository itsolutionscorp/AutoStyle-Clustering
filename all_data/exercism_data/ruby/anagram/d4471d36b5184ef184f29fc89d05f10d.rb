class Anagram
  def initialize string
    @string = string
  end

  def match array
    array.select { |word| match_string_sum? word.sum }
  end

  private
  attr_reader :string

  def match_string_sum? sum
    string.sum == sum
  end
end

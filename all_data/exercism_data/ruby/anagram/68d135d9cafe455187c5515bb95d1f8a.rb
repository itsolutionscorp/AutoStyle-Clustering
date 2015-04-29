class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(list)
    find_posibilities
    posibilities.select { |posibility| list.include?(posibility) }
  end

private

  def find_posibilities
    @all_possible ||= @word.chars.permutation.map(&:join).reverse
  end

  def posibilities
    @all_possible.delete(@word) 
    @all_possible.uniq
  end
end

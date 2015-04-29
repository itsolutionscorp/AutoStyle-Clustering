class Anagram < String
  def initialize(word)
    super(word.downcase)
  end

  def match(list)
    list.select { |w| w.downcase != self && w.downcase.chars.sort == self.chars.sort }
  end
end

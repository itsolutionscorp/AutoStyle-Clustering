class Raindrops
  def self.convert(number)
    words = magic_words_by(number)
    words.empty? ? number.to_s : words
  end

  private

  def self.magic_words_by(number)
    dividers_words = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    dividers_words.select do |divider, word|
      number.modulo(divider).zero?
    end.values.join
  end
end

class Phrase
  attr_reader :word_count

  def initialize(content)
    @word_count = content.split(' ')
                         .flat_map { |w| w.split(',') }
                         .map { |w| extract_word(w) }
                         .compact
                         .each_with_object(Hash.new(0)) { |e, a| a[e.downcase] += 1 }
  end

  private

  def extract_word(string)
    $1 if string.match(/([a-zA-Z0-9]+)/)
  end
end

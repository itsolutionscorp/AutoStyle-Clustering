class Crypto
  attr_accessor :phrase

  def initialize phrase
    @phrase = phrase.scan(/\w/).join.downcase
  end

  def normalize_plaintext
    phrase
  end

  def size
    Math.sqrt(phrase.length).ceil
  end

  def plaintext_segments
    phrase.scan(/.{1,#{size}}/)
  end

  def ciphertext
    (0..size).each_with_object([]) do |i, result|
      plaintext_segments.each { |letter| result << letter[i] }
    end.join
  end

  def normalize_ciphertext
    size > 2 ? show_ciphertext(1) : show_ciphertext(0)
  end

  private

  def show_ciphertext number
    ciphertext.scan(/.{1,#{size-number}}/).join(" ")
  end

end

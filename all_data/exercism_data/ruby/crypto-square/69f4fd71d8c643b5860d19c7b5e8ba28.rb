class Crypto
  attr_reader :plain
  private :plain

  def initialize(plain)
    @plain = plain
  end

  def normalize_plaintext
    plain.gsub(/\W/, '').downcase
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    chunk(normalize_plaintext, size)
  end

  # WIP I need to improve padding here.
  # But first: bake some Pizza ;)
  def ciphertext
    plaintext_segments.map { |x| ("%-#{size}s" % x).split("") }.transpose.flatten.join.gsub(" ", "")
  end

  def normalize_ciphertext
    chunk(ciphertext, 5).join(" ")
  end

  private

  def chunk(text, size)
    text.scan(/\w{1,#{size}}/)
  end
end

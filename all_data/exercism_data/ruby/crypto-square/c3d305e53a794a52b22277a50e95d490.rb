class Crypto
  def initialize(string)
    @string = String(string)
  end

  def normalize_plaintext
    @string.downcase.tr('^a-z0-9', '')
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def ciphertext
    plaintext_segments.each_with_object(Array.new(size) { '' }) do |segment, a|
      segment.each_char.with_index { |c, i| a[i] << c }
    end.join
  end

  def normalize_ciphertext
    ciphertext.scan(/.{1,#{plaintext_segments.count}}/).join(' ')
  end
end

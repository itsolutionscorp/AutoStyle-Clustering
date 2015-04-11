class Crypto
  def initialize(plaintext)
    @plaintext = plaintext
  end

  def normalize_plaintext
    @plaintext.downcase.gsub(/\W/, '')
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    chunk(normalize_plaintext, size)
  end

  def ciphertext
    size.times.with_object('') do |i, text|
      text << plaintext_segments.map { |segment| segment[i] }.join
    end
  end

  def normalize_ciphertext
    chunk(ciphertext, ((ciphertext.length)*1.0/size).ceil).join(' ')
  end

  def chunk(s, size)
    s.scan(/.{1,#{size}}/)
  end
end

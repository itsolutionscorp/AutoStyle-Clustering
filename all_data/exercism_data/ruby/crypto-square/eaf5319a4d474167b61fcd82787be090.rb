class Crypto
  def initialize(message)
    @message = message
  end

  def normalize_plaintext
    @normalized_plaintext ||= @message.downcase.gsub(/\s|[^a-z0-9]/, '')
  end

  def size
    square_root = Math.sqrt(normalize_plaintext.size)
    square_root == square_root.to_i ? square_root : (square_root.to_i + 1)
  end

  def plaintext_segments
    normalize_plaintext.scan(%r(.{1,#{size}}))
  end

  def ciphertext
    split_segments = plaintext_segments.map{|e|e.split('')}
    split_segments.shift.zip(*split_segments).join
  end

  def normalize_ciphertext
    ciphertext.scan(%r(.{1,5})).join(' ')
  end
end

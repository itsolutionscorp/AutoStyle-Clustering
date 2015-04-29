class Crypto
  def initialize(message)
    @message = message
  end

  def normalize_plaintext
    @message.chars.select {|c| c =~ /\w/}.join('').downcase
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.chars.each_slice(size).with_object([]) do |chunk,array|
      array << chunk.join('')
    end
  end

  def ciphertext
    total = []
    size.times do |i|
      total << plaintext_segments.each_with_object([]) do |letter, cipher|
        cipher << letter[i]
      end
    end
    total.flatten.join('')
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice(5).with_object([]) do |slice, normal|
      normal << slice.join
    end.join(' ')
  end
end

class Crypto
  def initialize(message)
    @message = message
    @normalize_text = normalize_plaintext
  end

  def size
    message_length = @normalize_text.length
    Math.sqrt(message_length).ceil
  end

  def normalize_plaintext
    @message.gsub(/[^a-zA-Z0-9]/, '').downcase
  end

  def plaintext_segments
    @normalize_text.chars.each_slice(size).map(&:join)
  end

  def ciphertext
    size.times.reduce("") do |result, n|
      result << plaintext_segments.map { |word| word[n]}.join
    end
  end

  def normalize_ciphertext
    rows = (@normalize_text.length / size.to_f).ceil
    ciphertext.chars.each_slice(rows).map(&:join).join(" ")
  end
end

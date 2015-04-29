class Crypto
  def initialize(message)
    @message = message
    @normalize_text = normalize_plaintext
  end

  def size
    message_length = @normalize_text.length
    size = is_perfect_square?(message_length) ? message_length : find_next_perfect_square(message_length)
    Math.sqrt(size).to_i
  end

  def normalize_plaintext
    @message.gsub(/[^a-zA-Z0-9]/, '').downcase
  end

  def plaintext_segments
    @normalize_text.chars.each_slice(size).map(&:join)
  end

  def ciphertext
    cipher = []
    size.times do |n|
      cipher[n] = ""
      plaintext_segments.each do |word|
        cipher[n] += word[n] if word[n]
      end
    end
    cipher.join
  end

  def normalize_ciphertext
    rows = (@normalize_text.length / size.to_f).ceil
    ciphertext.chars.each_slice(rows).map(&:join).join(" ")
  end

  private

  def is_perfect_square?(number)
    squared = Math.sqrt(number)
    squared.to_i ** 2 == number
  end

  def find_next_perfect_square(number)
    number += 1 until is_perfect_square?(number)
    number
  end
end

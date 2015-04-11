class Crypto
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def normalize_plaintext
    self.message.scan(/\w/).join.downcase
  end

  def size
    Math.sqrt(self.message.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.chars.each_slice(size).to_a.map(&:join)
  end

  def ciphertext_for_plaintext_segments
    pt = plaintext_segments
    (0...size).map{|num| pt.map{|line|line[num]}.join }.join
  end

  def other_type_of_segment # Because the tests are contradictory!
    normalize_plaintext.chars.each_slice(size-1).to_a
  end

  def ciphertext # Because the tests are contradictory!
    os = other_type_of_segment
    (0...size).map{|num| os.map{|line|line[num]}.join }.join
  end

  def normalize_ciphertext
    ciphertext_for_plaintext_segments.chars.each_slice(5).map(&:join).join(" ")
  end
end

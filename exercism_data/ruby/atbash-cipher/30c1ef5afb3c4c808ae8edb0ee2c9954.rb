class Atbash

  def self.encode(message)
    space_delimit base_encode(message)
  end

  private

  def self.base_encode(message)
    normalize(message).chars.inject('') do |cipher, char|
      cipher + cipher_legend[char]
    end
  end

  def self.normalize(message)
    message.downcase.tr("^a-z0-9", "")
  end

  def self.space_delimit(message)
    message.scan(/.{1,5}/).join(" ")
  end

  def self.cipher_legend
    @cipher_legend ||= build_legend
  end

  def self.build_legend
    letters = Hash[('a'..'z').zip ('a'..'z').to_a.reverse]
    numbers = Hash[('0'..'9').zip ('0'..'9')]
    letters.merge numbers
  end

end

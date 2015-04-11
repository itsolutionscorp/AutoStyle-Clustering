class Atbash
  @cipher_table = Hash[('a'..'z').zip(('a'..'z').to_a.reverse)]

  class << self
    def encode(message)
      message.chars
        .map {|char| @cipher_table[char.downcase] || char if char =~ /[a-zA-Z1-9]/}
        .select {|encoded_char| encoded_char}
        .each_slice(5)
        .map {|five_chars| five_chars.join}
        .join ' '
    end
  end
end

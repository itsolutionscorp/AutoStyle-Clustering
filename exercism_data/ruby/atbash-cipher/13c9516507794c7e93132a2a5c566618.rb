class Atbash
  ALPHABET = *'a'..'z'
  CIPHER =  ALPHABET.reverse

  def self.encode text
    text.gsub(/\s+/, '')
        .chars
        .map { |c| swap c }
        .compact
        .each_slice(5)
        .map(&:join)
        .join ' '
  end

  def self.swap c
    c = c.downcase
    if c.match /[0-9]/
      c
    elsif c.match /\W/
      nil
    else
      CIPHER[ALPHABET.index c]
    end
  end
end

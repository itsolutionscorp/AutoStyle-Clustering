## rubocop:disable Documentation
class Atbash
  ALPHABET = ('a'..'z').to_a.join
  ALPHABET_REVERSE = ALPHABET.reverse

  CYPHER_WORD_LENGTH = 5

  def self.encode(plaintext)
    cyphertext = clean(plaintext).tr(ALPHABET, ALPHABET_REVERSE)
    create_groups(cyphertext)
  end

  def self.clean(text)
    text.downcase.gsub(/\W/, '')
  end

  def self.create_groups(cyphertext)
    cyphertext.chars.each_slice(CYPHER_WORD_LENGTH)
      .map(&:join)
      .join ' '
  end
end

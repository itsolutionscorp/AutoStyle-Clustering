module Atbash
  ALPHABET = {
    standard: ('a'..'z').to_a.join,
    encoded: ('a'..'z').to_a.reverse.join
  }

  def self.encode(str)
    str.downcase.tr(ALPHABET[:standard], ALPHABET[:encoded]).gsub(/\s|\W/, '')
      .chars.each_slice(5).to_a.map(&:join).join(' ')
  end
end

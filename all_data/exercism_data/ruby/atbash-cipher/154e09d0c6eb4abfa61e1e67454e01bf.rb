module Atbash
  def self.encode input
    chars = ('a'..'z').to_a.join
    input.downcase
         .gsub(/\W/, '')
         .tr(chars, chars.reverse)
         .scan(/.{1,5}/).join(' ')
  end
end

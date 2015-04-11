module Atbash
  def self.encode(string)
    string.downcase.tr('^a-z0-9', '')
      .tr('a-z', Array('a'..'z').reverse.join)
      .scan(/.{1,5}/).join(' ')
  end
end

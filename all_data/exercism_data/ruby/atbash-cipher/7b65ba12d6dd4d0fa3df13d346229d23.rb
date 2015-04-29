class Atbash
  PLAIN = 'abcdefghijklmnopqrstuvwxyz'
  CIPH = 'zyxwvutsrqponmlkjihgfedcba'

  def self.encode(text)
    ans = ""
    text.downcase.gsub(/[\.,\s+]/, "").each_char do |letter|
      PLAIN.include?(letter) ? ans += CIPH[PLAIN.index(letter)] : ans += letter
    end
    ans.scan(/.{5}|.+/).join(" ")
  end
end

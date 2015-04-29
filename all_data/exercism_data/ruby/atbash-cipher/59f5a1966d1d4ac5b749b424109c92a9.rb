class Atbash

  def self.encode(string)
    string.downcase.gsub(/[^a-z0-9]|\s+/, "").tr(FORWARDS, BACKWARDS).scan(/.{1,5}/).join(" ")
  end

  FORWARDS  = "abcdefghijklmnopqrstuvwxyz" 
  BACKWARDS = "zyxwvutsrqponmlkjihgfedcba"

end

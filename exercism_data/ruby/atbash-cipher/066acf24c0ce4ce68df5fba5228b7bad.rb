class Atbash
  PLAIN  = "abcdefghijklmnopqrstuvwxyz"
  CIPHER = "zyxwvutsrqponmlkjihgfedcba"

  def self.encode(input)
    input.
      downcase.
      delete("^a-z0-9").
      gsub(/\w{5}/, '\& ').strip.
      tr(PLAIN, CIPHER)
  end
end

class PigLatin
  def self.translate(words)
    words = words.split(" ")
    words.map do |word|
      letters = word.chars
      add_on_letters = []
      remaining_letters = letters
      qu_check = false
      yt_check = false
      xr_check = false
      letters.each do |letter|
        if yt_check == true && letter.match(/[t]/)
          add_on_letters = add_on_letters.slice(0...-1)
          remaining_letters = ['y'] + remaining_letters
          break
        elsif xr_check == true && letter.match(/[r]/)
          add_on_letters = add_on_letters.slice(0...-1)
          remaining_letters = ['x'] + remaining_letters
          break
        elsif letter.match(/[^aeiouqyx]/) || (qu_check == true && letter.match(/[u]/))
          add_on_letters << letter
          remaining_letters = remaining_letters.slice(1..-1)
          qu_check = false
          yt_check = false
          xr_check = false
        elsif letter.match(/[qyx]/)
          add_on_letters << letter
          remaining_letters = remaining_letters.slice(1..-1)
          qu_check = true
          yt_check = true
          xr_check = true
        else
          break
        end
      end
      (remaining_letters + add_on_letters + ['ay']).join
    end.join(" ")
  end
end

# Part 3

class String
  def sort_letters
    w = []
    each_char do |c| w << c end
    w.sort!
    @value = ""
    w.each do |c| @value = @value + c end

    @value
  end
end

# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  out = []

  words.each do |w|
    found = false
    out.each do |s|
      ws = w.downcase.sort_letters
      ss = s[0].downcase.sort_letters
      print(ws, " ", ss, "\n")

      if ws == ss then
        s << w
        found = true
        break
      end
    end

    out << [w] unless found
  end

  out
end

def combine_anagrams_test
  #  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
  puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
end
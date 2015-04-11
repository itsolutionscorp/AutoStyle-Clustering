class PigLatin

  VOWELS = ["a", "e", "i", "o", "u"]

  CONSONANTS = ["qu"] + ("a".."z").to_a - VOWELS

  def self.translate(string)
    string.split(" ").map do | word |
      translate_word(word)
    end.join(" ")
  end

private

#   def self.translate_word(string)
#     rest_of_word = ""
#     consonants = ""
#     string.each_char.with_index do | char, index |
#       if VOWELS.include?(char)
#         rest_of_word = string[index..-1]
#         break
#       else
#         if char == "q"
#           consonants += char + string[index+1]
#           rest_of_word = string[index+2..-1]
#           break
#         else
#           consonants += char
#         end
#       end
#     end
#     rest_of_word + consonants + "ay"
#   end
# end


  def self.translate_word(string)
    start_at = 0
    string.each_char.with_index do |char, index|
      if VOWELS.include?(char)
        start_at = index
        if char == "u" && string[index-1] == "q"
          start_at = index + 1
        end
        break
      end
    end
    string[start_at..-1] + string[0...start_at] + "ay"
  end
end

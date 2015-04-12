Phrase = Struct.new(:input) do
  SEPARATOR = /\W+/.freeze

  def word_count
    input
      .split(SEPARATOR)
      .group_by(&:downcase)
      .reduce({}) { |counts, (word, group)|
        counts.merge(word => group.size)
      }
  end
end

# Phrase = Struct.new(:input) do
#   WORD = /\w+/.freeze
# 
#   def word_count
#     input
#       .downcase
#       .scan(WORD)
#       .reduce(Hash.new(0)) { |counts, word|
#         counts[word] += 1
#         counts
#       }
#   end
# end

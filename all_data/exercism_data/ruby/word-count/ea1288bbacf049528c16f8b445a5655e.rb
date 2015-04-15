require 'delegate'
using ReusableStuff

class Phrase < SimpleDelegator
  def word_count
    words.count_by &:downcase
  end

  def words
    scan /\w+/
  end
end


BEGIN{ # in other file:

  module ReusableStuff
    refine Array do

      # more specific version of group_by
      #
      def count_by &block
        group_by(&block).map(&COUNT).reduce :merge
      end
      COUNT = -> (( k, v )) {{ k => v.count }}
    end
  end
}

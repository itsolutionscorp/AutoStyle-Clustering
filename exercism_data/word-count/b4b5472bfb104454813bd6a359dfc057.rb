require 'delegate'

class Phrase < SimpleDelegator
  def word_count
    words.count_by &:downcase
  end

  def words
    Words[scan WORDS]
  end
  WORDS = /\w+/


  class Words < SimpleDelegator
    singleton_class.send :alias_method, :[], :new

    def count_by &block
      group_by(&block).map(&COUNT).reduce :merge
    end
    COUNT = -> (( k, v )) {{ k => v.count }}
  end
end

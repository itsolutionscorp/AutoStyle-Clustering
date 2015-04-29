class Phrase < Wrapper
  def word_count
    normalize.tokenize.count_by
  end

  def normalize
    Phrase[downcase]
  end

  def tokenize
    Words[scan /\w+/]
  end
end


class Words < Wrapper
  def count_by &block
    block = block || proc { |x| x }
    group_by(&block).map(&COUNT).reduce :merge
  end
  COUNT = -> (( k, v )) {{ k => v.count }}
end


BEGIN{
  require 'delegate'
  class Wrapper < SimpleDelegator
    singleton_class.send :alias_method, :[], :new
  end
}

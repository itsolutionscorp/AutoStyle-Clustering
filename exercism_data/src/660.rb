class Phrase
  attr_accessor :string
  attr_accessor :words

  def initialize(string)
    @string = string.downcase    # make everything lowercase
    @words  = @string.split /\W/ # anything not a word char
    @words  = @words.delete_if {|c| c !~ /\w/ }

    @word_count = nil
  end

  def word_count
    return @word_count if @word_count

    # group them by word
    groups = words.group_by {|w| w }
    # get the size of each grouping
    counts = groups.map {|k, v| [k, v.size] }

    # turn it into a hash
    @word_count = counts.inject({}) {|h, (k, v)| h.merge k => v }
  end
  
end

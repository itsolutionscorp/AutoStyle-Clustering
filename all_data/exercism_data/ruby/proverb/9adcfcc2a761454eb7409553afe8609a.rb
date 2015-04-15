class Proverb

  def initialize(*words)
    parse_options(words)
  end

  def parse_options(options)
    @words ||= []
    @option = Hash.new
    options.each do |opt|
      if opt.class == Hash
        @option[opt.first[0]] = opt.first[1]
      else
        @words << opt
      end
    end
  end

  def proverb_start_line(word)
    "For want of a #{word} "
  end

  def proverb_end_line(word)
    "the #{word} was lost.\n"
  end

  def proverb_ending(word)
    if @option[:qualifier]
      "And all for the want of a #{@option[:qualifier]} #{word}."
    else
      "And all for the want of a #{word}."
    end
  end

  def build_proverb_line(count)
    proverb_start_line(@words[count]) + proverb_end_line(@words[count+1])
  end

  def to_s
    @output = ""
    @words.count.times do |count|
      if @words.count >= count
        @output += build_proverb_line(count) if @words[count+1]
      end
    end
    @output += proverb_ending(@words[0])
  end

end

class Proverb
  attr_reader :params, :options

  def initialize *params
    @params = *params
    @options = build_options
  end

  def build_options
    params.pop unless params[-1].class != Hash
  end

  def to_s
    build_song
  end

  def build_song
    params.each_with_index.map do |value, index|
      if index == params.length-1
        if options
          "And all for the want of a #{options[:qualifier]} #{params[0]}."
        else
          "And all for the want of a #{params[0]}."
        end
      else
        "For want of a #{value} the #{params[index+1]} was lost.\n"
      end
    end.join
  end

end

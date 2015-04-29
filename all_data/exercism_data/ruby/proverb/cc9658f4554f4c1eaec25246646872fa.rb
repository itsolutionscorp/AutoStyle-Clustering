class Proverb
  attr_reader :params

  def initialize *params
    @params = *params
  end

  def to_s
    return_array = []
    params.each_with_index do |value, index|
      if index == params.length-1 && params[index].class != Hash
        return_array << "And all for the want of a #{params[0]}."
      elsif index == params.length-1 && params[index].class == Hash
        #Do Nothing!
      elsif index == params.length-2 && params[-1].class == Hash
        return_array << "And all for the want of a #{params[-1][:qualifier]} #{params[0]}."
      else
        return_array << "For want of a #{value} the #{params[index+1]} was lost.\n"
      end
    end
    return_array.join
  end
end

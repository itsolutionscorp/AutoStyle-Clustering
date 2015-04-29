class Proverb
  def initialize(*args)
    @consequences = [*args]
    options = @consequences.last.class == Hash ? @consequences.pop : {}
    @qualifier = options.has_key?(:qualifier) ? options[:qualifier] + " " : ""
  end

  def to_s
    result = ""
    @consequences.each_with_index do |consequence, index|
      next if index == 0
      result << "For want of a #{@consequences[index - 1]} the #{consequence} was lost.\n"
      result << "And all for the want of a #{@qualifier + @consequences.first}." if @consequences[-1] == consequence
    end
    result
  end
end

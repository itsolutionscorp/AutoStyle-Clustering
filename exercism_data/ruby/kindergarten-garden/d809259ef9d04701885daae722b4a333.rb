#Jeff
require 'pry'
class Garden
  attr_reader :row_0, :row_1, :names

  def initialize(layout, names=nil)
    @row_0, @row_1 = layout.split("\n")
    @names = format_names(names) || default_names
    build_name_accessor_methods
  end

  def format_names(names)
    names.collect{|name| name.downcase.to_sym}.sort if names
  end

  def default_names
    [:alice, :bob, :charlie, :david, :eve, :fred, :ginny, :harriet, :ileana,
    :joseph, :kincaid, :larry]
  end

  def build_name_accessor_methods
    names.each_with_index do |name, position|
      define_singleton_method(name) do
        codes_to_plants( codes_for(position) )
      end
    end
  end

  def codes_for(position)
    start_at = 2 * position
    end_at = start_at + 1
    @row_0[start_at..end_at] + @row_1[start_at..end_at]
  end

  def codes_to_plants(codes)
    codes.chars.collect{|char| code_to_plant(char)}
  end

  def code_to_plant(code)
    case code
    when 'R' then :radishes
    when 'G' then :grass
    when 'V' then :violets
    when 'C' then :clover
    else raise("Don't have a plant for #{code.inspect}")
    end
  end


end

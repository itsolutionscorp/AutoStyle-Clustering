class Proverb < TemplatedText
  def initialize *causes, qualifier: nil
    super """
    <% causes_and_effects.each do |cause, effect| -%>
      For want of a <%= cause %> the <%= effect %> was lost.
    <% end -%>
    And all for the want of a <%= qualified first_cause %>."""

    @causes = causes
    @qualifier = qualifier
  end

  private

  attr_reader :causes, :qualifier

  def causes_and_effects
    causes.each_cons(2)
  end

  def first_cause
    causes.first
  end

  def qualified noun
    [qualifier, noun].compact.join " "
  end

end

BEGIN {
  require "erb"

  class TemplatedText < Struct.new(:template)

    def to_s
      @expanded_text ||= expand_template
    end

  private

    def expand_template
      ERB.new(template.gsub(/^\s*/,""),nil,"%<>-").result(binding)
    end
  end
}

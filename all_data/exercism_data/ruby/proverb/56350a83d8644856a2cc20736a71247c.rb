class Proverb
  def initialize(*wants, qualifier: '')
    @wants = wants
    @qualifier = qualifier.empty? ? ' ' : " #{qualifier} "
    @cache = nil

    @for_want_of_a_nail = {
      'nail' => "And all for the want of a#{@qualifier}nail.",
      'shoe' => "For want of a nail the shoe was lost.\n",
      'horse' => "For want of a shoe the horse was lost.\n",
      'rider' => "For want of a horse the rider was lost.\n",
      'message' => "For want of a rider the message was lost.\n",
      'battle' => "For want of a message the battle was lost.\n",
      'kingdom' => "For want of a battle the kingdom was lost.\n"
    }
  end

  def to_s
    @wants.shift
    @cache ||= @wants.each.inject("") { |proverb, want| proverb + @for_want_of_a_nail [want] } + @for_want_of_a_nail['nail']
  end
end

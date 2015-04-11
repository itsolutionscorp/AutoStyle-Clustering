class Bob
  
  def hey( question )
    answer, _ = ANSWERS.detect { |a, regs| regs.any? { |regex| question =~ regex } }   

    answer
  end

  private

  # Answer => [ List of RegEx ]
  ANSWERS = {
    'Fine. Be that way!' => [/\A *\Z/],
    'Woah, chill out!' => [/^[A-Z ]+$/, /^[A-Z \*\$\^\(\)%@?!,#0-3]+[?!]$/],
    'Sure.' => [/\?\Z/],
    'Whatever.' => [/.*/]
  }
end

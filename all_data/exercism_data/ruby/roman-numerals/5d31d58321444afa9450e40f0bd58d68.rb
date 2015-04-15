class Integer
  @@roman_atoms = [
      [1000,'M'],
      [900,'CM'],
      [500,'D'],
      [400,'CD'],
      [100,'C'],
      [90 ,'XC'],
      [50,'L'],
      [40,'XL'],
      [10,'X'],
      [9,'IX'],
      [5,'V'],
      [4, 'IV'],
      [1,'I']
  ]

  # Integer#to_roman converts an integer <= 3000 to its Roman numeral representation. Representation is built by
  # repeatedly finding the next biggest Roman numbering 'atom' and its associated string representation,
  # appending that atom's representation to the overall representation, and adjusting the remaining numeric value still
  # to be converted
  def to_roman_i
    residual = self
    index = 0
    roman_numeral_rep = ''
    while residual > 0
      current_atom = @@roman_atoms[index][0]
      current_atom_rep = @@roman_atoms[index][1]
      if residual >= current_atom
        roman_numeral_rep << current_atom_rep
        residual -= current_atom
      else
        index += 1
      end
    end
    roman_numeral_rep
  end

  # Functional version of code that converts a number to its Roman numeral representation. Same algorithm as iterative
  # version implemented in Integer#to_roman. Marginally less efficient than iterative version because
  # @@roman_atoms array is searched multiple times + recursion leads to more stack frames.
  def to_roman
    return '' if self == 0
    threshold, rep_fragment = @@roman_atoms.find { |threshold, _| self >= threshold}
    return rep_fragment + (self - threshold).to_roman
  end

end

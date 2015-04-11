ALLERGIES = [
 'cats', 'pollen', 'chocolate', 'tomatoes',
 'strawberries', 'shellfish', 'peanuts', 'eggs',
  ]

class Allergies
  def initialize(n)
    @b = n.to_s(2).rjust(8,'0')[-8..-1].chars
  end

  def list
    @b.zip(ALLERGIES).inject([]) do |a, z|
      a.unshift(z[1]) if z[0] == '1'; a
    end
  end

  def allergic_to?(a)
    @b[ALLERGIES.index(a)] == '1'
  end
end

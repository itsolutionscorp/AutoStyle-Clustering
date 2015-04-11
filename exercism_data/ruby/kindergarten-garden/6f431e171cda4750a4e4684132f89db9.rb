class Garden
  PLANTS = { radishes: 'R',
             clover:   'C',
             grass:    'G',
             violets:  'V' }

  CHILDREN = [:alice, :bob, :charlie, :david, :eve, :fred,
              :ginny, :harriet, :ileana, :joseph, :kincaid,
              :larry]

  def initialize diagram, students = CHILDREN
    students.sort.map { |s| s.downcase.to_sym }.each.with_index do |name, i|
      d = diagram.split("\n")
      instance_eval %{ def #{name}
                         #{d}.each_with_object([]) do |row, list|
                           row_plants = row.chars.values_at(#{i * 2}, #{i * 2 + 1})
                           row_plants.each { |p| list << PLANTS.invert[p] }
                         end
                       end }
    end
  end
end

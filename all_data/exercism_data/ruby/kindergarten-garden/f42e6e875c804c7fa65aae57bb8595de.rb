require 'pry'
class Garden
  attr_reader :plant_string, :first_row_of_plants, :second_row_of_plants

  def initialize(plant_string, diagram)
    @plant_string = plant_string
    @first_row_of_plants = plants[0..plants.length/2 - 1]
    @second_row_of_plants = plants[plants.length/2..plants.length-1]
    @diagram = diagram
  end

  def plant_code
    { "R" => :radishes,
      "C" => :clover,
      "V" => :violets,
      "G" => :grass
    }
  end

  def plants
    plants = plant_string.chars
    plants.delete("\n")
    plants
  end

  def alice
    alices_plants = first_row_of_plants[0..1] + second_row_of_plants[0..1]
    alices_plants.map { |plant| plant_code[plant] }
  end

  def bob
    bobs_plants = first_row_of_plants[2..3] + second_row_of_plants[2..3]
    bobs_plants.map { |plant| plant_code[plant] }
  end

  def charlie
    charlies_plants = first_row_of_plants[4..5] + second_row_of_plants[4..5]
    charlies_plants.map { |plant| plant_code[plant] }
  end

  def david
    davids_plants = first_row_of_plants[6..7] + second_row_of_plants[6..7]
    davids_plants.map { |plant| plant_code[plant] }
  end

  def eve
    eves_plants = first_row_of_plants[8..9] + second_row_of_plants[8..9]
    eves_plants.map { |plant| plant_code[plant] }
  end

  def fred
    freds_plants = first_row_of_plants[10..11] + second_row_of_plants[10..11]
    freds_plants.map { |plant| plant_code[plant] }
  end

  def ginny
    ginnys_plants = first_row_of_plants[12..13] + second_row_of_plants[12..13]
    ginnys_plants.map { |plant| plant_code[plant] }
  end

  def harriet
    harriets_plants = first_row_of_plants[14..15] + second_row_of_plants[14..15]
    harriets_plants.map { |plant| plant_code[plant] }
  end

  def ileana
    ileanas_plants = first_row_of_plants[16..17] + second_row_of_plants[16..17]
    ileanas_plants.map { |plant| plant_code[plant] }
  end

  def joseph
    josephs_plants = first_row_of_plants[18..19] + second_row_of_plants[18..19]
    josephs_plants.map { |plant| plant_code[plant] }
  end

  def kincaid
    kincaids_plants = first_row_of_plants[20..21] + second_row_of_plants[20..21]
    kincaids_plants.map { |plant| plant_code[plant] }
  end

  def larry
    larrys_plants = first_row_of_plants[22..23] + second_row_of_plants[22..23]
    larrys_plants.map { |plant| plant_code[plant] }
  end
end

#trouble with making DisorderedTest class tests pass
#starting on line 86 in the test file

class Robot

  LETTERS = %w(A B C D E F G H I J K L M N O P Q R S T U V W X Y Z)
  NUMBERS = [1, 2, 3, 4, 5, 6, 7, 8, 9]

  def initialize
    generate_name
  end

  def name
    @generated_name
  end

  def reset
    generate_name
  end

  def generate_name
    @generated_name_array = []
    generate_letters
    generate_numbers
    @generated_name = @generated_name_array.join
  end

  def generate_letters
    2.times do
      @generated_name_array.push(LETTERS.sample)
    end
  end

  def generate_numbers
    3.times do
      @generated_name_array.push(NUMBERS.sample)
    end
  end
 
end

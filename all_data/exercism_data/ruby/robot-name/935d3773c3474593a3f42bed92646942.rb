class Robot
  def name
    @name ||= make_name
  end

  def make_name
    "asimov-#{MakeId.assemble}"
  end

  def reset
    @name = nil
  end
end

class MakeId

  def self.assemble
    " " << random_letter + random_letter + random_number
  end

  def self.random_letter
    up_and_downcase_letters_append.sample
  end

  def self.random_number
    (0..999).to_a.sample.to_s
  end

  def self.up_and_downcase_letters_append
    upcase, downcase = [("A".."Z").to_a, ("a".."z").to_a].flatten
  end
end

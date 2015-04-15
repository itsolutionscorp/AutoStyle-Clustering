class Gigasecond

def self.from birth_date
   gs = GsAnniversaryCalculator.new
   gs.calculate_1gs_anniversary birth_date
end

end

class GsAnniversaryCalculator

GIGASECOND = 10**9

def calculate_1gs_anniversary birth_date
   add_1gs_to_birth_date(birth_date).to_date   	
end

def add_1gs_to_birth_date birth_date
   birth_date.to_time + GIGASECOND
end
end

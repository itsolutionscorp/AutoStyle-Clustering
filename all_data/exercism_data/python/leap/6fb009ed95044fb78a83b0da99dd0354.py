class Year(int):
    def is_divisible_by(self, number):
        """Return whether this year is divisible by given number."""
        return self % number == 0
    
    def is_leap_year(self):
        """Return whether this year is a leap year."""
        return (self.is_divisible_by(4) and
                not self.is_divisible_by(100)) or self.is_divisible_by(400)

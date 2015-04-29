class Luhn:
    def addends(num):
        digits = map(int, list(str(num)))[::-1]
        
        for i in range(2, len(digits)+1, 2):
            digits[i] *= 2
            if digits[i]>10:
                digits[i] -= 9
        
        return digits[::-1]
    
    def checksum(num):
        total = sum(self.addends(num))
        if total%10:
            return 10 - total%10
        return 0
    
    def is_valid(num):
        total = sum(self.addends(num))
        return not total%10
    
    def create(num):
        checksum = self.checksum(num)
        return num*10 + checksum
